console.log('Hello World !!')

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

function submitForm(formId) {
    var form = document.getElementById(formId);
    console.log('Form', form);
    var jsonReq = toJSONString(form);
}

function closeForm(formId) {
    var form = document.getElementById(formId);
    form.style['display'] = 'none';
}

function toJSONString(form) {
    var obj = {};
    var elements = form.getElementsByTagName('input');
    for( var i = 0; i < elements.length; ++i ) {
        var element = elements[i];
        var name = element.name;
        var value = element.value;

        if(name) {
            if(element.type == 'radio') {
                if(element.checked) {
                    obj[name] = value;
                }
            }
            else {
                obj[name] = value;
            }
        }
    }
    console.log('JSON', obj);
}

function resetText(elem) {
    elem.value = '';
}