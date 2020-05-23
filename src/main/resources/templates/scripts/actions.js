console.log('Hello World !!')

function toggleOptions() {
    var menuClose = document.getElementById("menu-closed");
    var menuOpen = document.getElementById("menu-opened");
    var options = document.getElementsByClassName("menu-opened");

    if(menuClose.style['display'] != 'none') {
        menuClose.style['display'] = 'none';
        menuOpen.style['display'] = 'inherit';
        for(var i = 0; i < options.length; i++) {
            options[i].style['display'] = 'inherit';
        }
    }
    else {
        menuClose.style['display'] = 'inherit';
        menuOpen.style['display'] = 'none';
        for(var i = 0; i < options.length; i++) {
            options[i].style['display'] = 'none';
        }
    }
}

function add() {
    console.log('Selected Add Option');
}

function remove() {
    console.log('Selected Remove Option');
}

function update() {
    console.log('Selected Update Option');
}
