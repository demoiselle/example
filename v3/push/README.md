# PUSH App
Aplicação "Servidor de push" com Demoiselle v3.0.

O Push server permite enviar mensagens para suas apps a partir de um servidor dentro de um canal determinado por você.

Página para teste - https://www.websocket.org/echo.html
coloque o endereço do websocket na página

wss://push.demoiselle.estaleiro.serpro.gov.br/ws/push/[Canal] - crie seu canal colocando um UUID ou qualquer string em [Canal]

Vá em https://push.demoiselle.estaleiro.serpro.gov.br/ws/ (Servidor Wildfly 10.1.0 com WebSocket) 
Encontre o Serviço Push e clique em POST

Em body coloque o json
{"event": "Nome da sua preferencia","recipient": "UUID do seu canal","message": "Mensagem a ser exibida"} 

Se você preferir, pode enviar uma mensagens fazendo uma requisição POST para 
https://push.demoiselle.estaleiro.serpro.gov.br/ws/api/push

Seu canal receberá a mensagem. 

* Canal que envia data e hora a cada minuto
* wss://push.demoiselle.estaleiro.serpro.gov.br/ws/push/time

* Canal que envia uma mensagem a cada 3 minutos
* wss://push.demoiselle.estaleiro.serpro.gov.br/ws/push/echo

```bash
# JS para escutar o websocket

<script language="javascript" type="text/javascript">

  var wsUri = "wss://push-fwkdemoiselle.rhcloud.com:8443/push/[Canal]";
  var output;

  function init()
  {
    output = document.getElementById("output");
    testWebSocket();
  }

  function testWebSocket()
  {
    websocket = new WebSocket(wsUri);
    websocket.onopen = function(evt) { onOpen(evt) };
    websocket.onclose = function(evt) { onClose(evt) };
    websocket.onmessage = function(evt) { onMessage(evt) };
    websocket.onerror = function(evt) { onError(evt) };
  }

  function onOpen(evt)
  {
    writeToScreen("CONNECTED");
    doSend("WebSocket rocks");
  }

  function onClose(evt)
  {
    writeToScreen("DISCONNECTED");
  }

  function onMessage(evt)
  {
    writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data+'</span>');
    websocket.close();
  }

  function onError(evt)
  {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
  }

  function doSend(message)
  {
    writeToScreen("SENT: " + message);
    websocket.send(message);
  }

  function writeToScreen(message)
  {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
    output.appendChild(pre);
  }

  window.addEventListener("load", init, false);

  </script>

  <h2>WebSocket Test</h2>

  <div id="output"></div>
```
