var arrayMandatories;
var arrayEnteros;
var arrayNoSuperarCaracteres;


function savePedido() {
    var inputs = initInputs();
    if (validateFields(inputs)) {
        ajaxRequest(createPedido(inputs));
    }
}

function initInputs() {
    var inputs = {
        nombreInput: {
            label: document.getElementById('nombreLabel').htmlFor,
            value: document.getElementById("nombre").value
        },
        montoInput: {
            label: document.getElementById('montoLabel').htmlFor,
            value: document.getElementById("monto").value
        },
        descuentoInput: {
            label: document.getElementById('descuentoLabel').htmlFor,
            value: document.getElementById("descuento").value
        }
    };
    return inputs;
}


function validateFields(inputs) {
    var message = '';
    addRestrictions(inputs);
    message = writeMessage();
    if (message !== '') {
        alert(message);
        return false;
    }
    return true;
}


function createPedido(inputs) {
    var pedido = {
        nombre: inputs.nombreInput.value,
        monto: inputs.montoInput.value,
        descuento: inputs.descuentoInput.value
    };
    return pedido;
}


function ajaxRequest(pedido) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4) {
            if (this.status == 201) {
                var pedidoRes = JSON.parse(this.responseText);
                alert("El pedido fue creado a nombre de:" + pedidoRes.nombre);
            } else {
                var response = JSON.parse(this.responseText);
                alert("Error:" + response.message);
            }
        }
    };
    xhttp.open("POST", "/pedidos/guardar", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(pedido));
}

function writeMessage() {
    var mandatoriesResults = validateMandatories(arrayMandatories);
    mandatoriesResults.push.apply(mandatoriesResults, validateIntegers(arrayEnteros));
    mandatoriesResults.push.apply(mandatoriesResults, validateCharacterLimits(arrayNoSuperarCaracteres));
    var message = '';
    for (var i = 0; i < mandatoriesResults.length; i++) {
        obj = mandatoriesResults[i];
        message += obj;
    }
    return message;
}

function addRestrictions(inputs) {
    arrayMandatories = [];
    arrayEnteros = [];
    arrayNoSuperarCaracteres = [];
    arrayMandatories.push(inputs.nombreInput);
    arrayMandatories.push(inputs.montoInput);
    arrayEnteros.push(inputs.montoInput);
    arrayEnteros.push(inputs.descuentoInput);
    arrayNoSuperarCaracteres.push(inputs.nombreInput);
}


function validate(array, callbackCustomValidate) {
    var arrayMsg = [];
    var arrayLength = array.length;
    for (var i = 0; i < arrayLength; i++) {
        obj = array[i];
        var msg = callbackCustomValidate(obj);
        if (msg != null) {
            arrayMsg.push(msg);
        }
    }
    return arrayMsg;
}


function callbackMandatories(obj) {
    var msg = null;
    if (obj.value.length == 0) {
        msg = "El campo " + obj.label + " es obligatorio. \n";
    }
    return msg;
}

function callbackIntegers(obj) {
    var msg = null;
    var regex = /^\+?\d+$/;
    if (!regex.test(obj.value)) {
        msg = "El campo " + obj.label + " debe ser del tipo integer. \n";
    }
    return msg;
}

function callbackCharectersLimit(obj) {
    var limitNumber = 100;
    var msg = null;
    if (obj.value.length > limitNumber) {
        msg = "El campo " + obj.label + " no puede superar los 100 caracteres. \n";
    }
    return msg;
}

function validateMandatories(arrayMandatories) {
    return validate(arrayMandatories, callbackMandatories);
}

function validateIntegers(arrayIntegers) {
    return validate(arrayIntegers, callbackIntegers);
}

function validateCharacterLimits(arrayCharacterLimits) {
    return validate(arrayCharacterLimits, callbackCharectersLimit);
}