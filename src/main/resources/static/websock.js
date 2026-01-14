const socket = new WebSocket('ws://localhost:3000/ws');

socket.onerror = (ev) => {
    console.error(ev);
}

socket.onopen = (ev) => {
    socket.send('Opened Socket!');
}

socket.onmessage = (ev) => {
    console.log(ev.data); // Later this will be changed to use the Protocol
}