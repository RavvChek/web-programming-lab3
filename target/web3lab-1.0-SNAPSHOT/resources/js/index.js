import AreaCheck from "./utilities/services/AreaCheck.js";
import Graph from "./utilities/services/Graph.js";
import Validator from "./utilities/services/Validator.js";

const submitButton = document.querySelector("input[type=submit]");
const graph = new Graph();
const svgElem = document.querySelector("svg");
document.addEventListener('DOMContentLoaded', () => {
    const x = document.getElementById("graph-form:x-value_input").value;
    const y = document.getElementById("graph-form:y-value").value;
    const r = document.getElementById("graph-form:r-value").value;
    graph.restoreDots();
    graph.redrawGraph();
    document.getElementById("graph-form:j_idt41").onclick = () => {
        graph.redrawGraph();
        graph.clearDots();
    }
    submitButton.onclick = () => {
        const x = document.getElementById("graph-form:x-value_input").value;
        const y = document.getElementById("graph-form:y-value").value;
        const r = document.getElementById("graph-form:r-value").value;
        if (!x || !y || !r || !Validator.isValid(x, y, r)) {
            return;
        }

        const isHit = AreaCheck.isHit(x, y, r);

        graph.drawDot(x * 20 + 150, 150 - y * 20, r, isHit);
    };
})

svgElem.addEventListener('click', (event) => {
    const clickPoint = graph.getClickPoint(event);
    const r = document.getElementById("graph-form:r-value").value;

    if (r === undefined) {
        return;
    }

    const relativeUnit = 20;

    document.getElementById("graph-form-hidden:x-value-hidden_input").value = ((clickPoint.x - 150) / relativeUnit).toFixed(3);
    document.getElementById("graph-form-hidden:x-value-hidden_hinput").value = ((clickPoint.x - 150) / relativeUnit).toFixed(3);
    document.getElementById("graph-form-hidden:y-value-hidden_input").value = ((150 - clickPoint.y) / relativeUnit).toFixed(3);
    document.getElementById("graph-form-hidden:y-value-hidden_hinput").value = ((150 - clickPoint.y) / relativeUnit).toFixed(3);
    document.getElementById("graph-form-hidden:r-value-hidden_input").value = r;
    document.getElementById("graph-form-hidden:r-value-hidden_hinput").value = r;
    document.getElementById("graph-form-hidden:j_idt47").click();

    const isHit = AreaCheck.isHit(((clickPoint.x - 150) / relativeUnit).toFixed(3),
        ((150 - clickPoint.y) / relativeUnit).toFixed(3),
        r);
    graph.drawDot(clickPoint.x, clickPoint.y, r, isHit);
});

const yInput = document.getElementById("graph-form:y-value");
yInput.addEventListener('input', () => {
    const messages = document.getElementById('messagesC');
    let yValid = false;
    let yValue = yInput.value.trim();
    const isValid = /^(-?[1-3]|[0-4])(?:[\.,]\d+)?$/.test(yValue);
    yValue = parseFloat(yInput.value.trim().replace(',', '.'));

    if (!isNaN(yValue)) {
        yValid = isValid;
    } else {
        yValid = false;
        messages.innerText = 'Invalid value of Y (a number in the range (-3;3))';
    }

    if (!yValid) {
        messages.innerText = 'Invalid value of Y (a number in the range (-3;3))';
    } else {
        yInput.value = yInput.value.replace(',', '.');
        messages.innerText = '';
    }
});
// function changeR(){
//     graph.redrawGraph();
//     graph.clearDots();
// }
