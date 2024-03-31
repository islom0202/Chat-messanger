'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
var chatHeader = document.querySelector('.chat-header');

var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    username = document.querySelector('#name').value.trim();

    if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');
        chatPage.classList.add('joining-time');

        var timeElement = document.createElement('span');
        timeElement.classList.add('joining-time');
        var timeText = document.createTextNode(message.time);
        timeElement.appendChild(timeText);
        chatPage.appendChild(timeElement);

        var socket = new SockJS('/websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function() {
            stompClient.subscribe('/topic/public', function(response) {
                displayChatHistory(JSON.parse(response.body));
            } ,
                function (){
                showTyping();
                },
            onMessageReceived);
            stompClient.send("/app/chat-history", {});

            onConnected();
        }, onError);
    }
    event.preventDefault();
}

function displayChatHistory(messages) {
    var chatHistoryElement = document.querySelector('#chatHistory');

    messages.forEach(function(message) {
        var messageElement = document.createElement('li');
        messageElement.classList.add('chat-message');
        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender.charAt(0));
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);

        var timeElement = document.createElement('span');
        timeElement.classList.add('message-time');
        var timeText = document.createTextNode( message.time);
        timeElement.appendChild(timeText);

        var textElement = document.createElement('p');
        textElement.textContent = message.content;

        messageElement.appendChild(avatarElement);
        messageElement.appendChild(usernameElement);
        messageElement.appendChild(textElement);
        messageElement.appendChild(timeElement);
        chatHistoryElement.appendChild(messageElement);

        messageArea.appendChild(messageElement);
        messageArea.scrollTop = messageArea.scrollHeight;
    }
    );
}

function showTyping() {
    var typingElement = document.getElementById("typing");
    stompClient.send("/app/typing", {});
    typingElement.textContent = "typing...";

}

function stopTyping(){
    var stopTypingElement = document.getElementById("typing");
    stompClient.send("/app/stop-typing",{});
    stopTypingElement.textContent = "";
}

window.onload = function () {
    connect();
    document.getElementById("message").addEventListener("input", function () {
        showTyping(true);
    });

    document.getElementById("message").addEventListener("blur", function () {
        showTyping(false);
    });
}

function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat-add-user",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )
    chatHeader.style.color = 'blue';
    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var currentTime = new Date();
        var date = currentTime.getDate();
        var hours = currentTime.getHours();
        var minutes = currentTime.getMinutes();
        var seconds = currentTime.getSeconds();

        hours = (hours < 10 ? '0' : '') + hours;
        minutes = (minutes < 10 ? '0' : '') + minutes;
        seconds = (seconds < 10 ? '0' : '') + seconds;

        var time = hours + ':' + minutes + ':' + seconds;
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT',
            time: time
        };
        stompClient.send("/app/chat-sending", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';

    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }


    var timeElement = document.createElement('span');
    timeElement.classList.add('message-time');
    var timeText = document.createTextNode( message.time);
    timeElement.appendChild(timeText);
    messageElement.appendChild(timeElement);

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)