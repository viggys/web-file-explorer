var baseUri = [[@{/}]];
var isMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);

/*if(isMobile) {
    console.log('Loading mobile view: ', isMobile);
    var footer = document.getElementsByClassName('foot-container')[0];
    console.log('footer', footer);
    footer.style.flexDirection = 'column';
//    footer.style['padding'] = '0.25em 0.5em';
}*/

function getCurrentPathUri() {
    console.log('baseUri: ', baseUri);
    var currentPathUri = '/' + location.pathname.split(baseUri)[1];
    console.log('currentPathUri: ', currentPathUri);
    return currentPathUri;
}

function toggleOptions() {
    var menu = document.getElementById("menu");
    var options = document.getElementsByClassName("menu-opened");

    if(menu.innerHTML == 'menu') {
        menu.innerHTML = 'menu_open';
        for(var i = 0; i < options.length; i++) {
            options[i].style['display'] = 'table-row';
        }
    }
    else {
        menu.innerHTML = 'menu';
        for(var i = 0; i < options.length; i++) {
            options[i].style['display'] = 'none';
        }
    }
}

function toggleHidden() {
    var hidden = document.getElementById('hidden');
    var hiddenRows = document.getElementsByClassName('hidden');

    if(hidden.innerHTML == 'visibility') {
        hidden.innerHTML = 'visibility_off';
        for(var i = 0; i < hiddenRows.length; i++) {
            hiddenRows[i].style['display'] = 'table-row';
        }
    }
    else {
        hidden.innerHTML = 'visibility';
        for(var i = 0; i < hiddenRows.length; i++) {
            hiddenRows[i].style['display'] = 'none';
        }
    }
}

function add() {
    console.log('Selected Add Option');
    var addForm = document.getElementById('addForm');
    addForm.reset();
    addForm.style['display'] = 'flex';
}

function remove() {
    console.log('Selected Remove Option');
}

function update() {
    console.log('Selected Update Option');
}

function logout() {
    location.replace([[@{/logout}]]);
}

function submitForm(formId) {
    var form = document.getElementById(formId);
    var path = form.elements['path'];
    if(path) {
        path.value = getCurrentPathUri();
    }
    sendPostHttpRequest(formId, form.action, toJSONString(form));
    closeForm(formId);
}

function closeForm(formId) {
    var form = document.getElementById(formId);
    form.style['display'] = 'none';
    form.reset();
}

function successNotify(formId) {
    var notify = document.getElementById(formId + 'Success');
    notify.style['display'] = 'flex';
    setTimeout(function() {
        notify.style['display'] = "none";
        location.reload();
    },
    2000);
}

function failNotify(formId) {
    var notify = document.getElementById(formId + 'Failed');
    notify.style['display'] = 'flex';
    setTimeout(function() { notify.style['display'] = "none"; }, 2000);
}

function toJSONString(form) {
    var obj = {};
    var elements = form.getElementsByTagName('input');
    for( var i = 0; i < elements.length; ++i ) {
        var element = elements[i];
        var id = element.id;
        var type = element.type;
        var name = element.name;
        var value = element.value;

        if(type == 'radio') {
            value = element.checked;
        }

        if(id || name) {
            if(id) {
                obj[id] = value;
            }
            else {
                obj[name] = value;
            }
        }
    }
    console.log('JSON request: ', obj);
    return obj;
}

function sendPostHttpRequest(formId, url, body) {
    var http = new XMLHttpRequest();
    http.onreadystatechange = function() {
        if (http.readyState == 4) {
            if(http.status >= 200 && http.status < 300) {
                successNotify(formId);
            }
            else {
                failNotify(formId);
            }
        }
    };
    http.open('POST', url, true);
    http.setRequestHeader('X-CSRF-TOKEN', body['_csrf']);
    http.setRequestHeader('Content-Type', 'application/json');
    delete body['_csrf'];
    http.send(JSON.stringify(body));
}