import protobuf from "protobufjs";

const root = await protobuf.load("message.proto");
const Packet = root.lookupType("com.example.protosocket.Packet");

const socket = new WebSocket('ws://localhost:3000/ws');
socket.binaryType = "arraybuffer";

socket.onerror = (ev) => {
    console.error(ev);
}

socket.onopen = (ev) => {
    console.log("Opening Socket...");
}

socket.onmessage = (ev) => {
    const bytes = new Uint8Array(ev.data);
    const message = Packet.decode(bytes);

    const object = Packet.toObject(message, {
        bytes: String,
    });
    console.log(object);
};

export function sendMessage(msg) {
    let packetObj = {
        to: 0x1,
        data: msg
    };
    const message = Packet.create(packetObj);
    const bytes = Packet.encode(message).finish();
    socket.send(bytes.buffer) // will be modified
}