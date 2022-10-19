var canvas;
var gl;
var program;

var aspect;

var mProjectionLoc, mModelViewLoc;

var matrixStack = [];
var modelView;

var time = 0;

// Stack related operations
function pushMatrix() {
    var m = mat4(modelView[0], modelView[1],
        modelView[2], modelView[3]);
    matrixStack.push(m);
}
function popMatrix() {
    modelView = matrixStack.pop();
}
// Append transformations to modelView
function multMatrix(m) {
    modelView = mult(modelView, m);
}
function multTranslation(t) {
    modelView = mult(modelView, translate(t));
}
function multScale(s) {
    modelView = mult(modelView, scalem(s));
}
function multRotationX(angle) {
    modelView = mult(modelView, rotateX(angle));
}
function multRotationY(angle) {
    modelView = mult(modelView, rotateY(angle));
}
function multRotationZ(angle) {
    modelView = mult(modelView, rotateZ(angle));
}

function fit_canvas_to_window() {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;

    aspect = canvas.width / canvas.height;
    gl.viewport(0, 0, canvas.width, canvas.height);

}

window.onresize = function () {
    fit_canvas_to_window();
}

window.onload = function () {
    canvas = document.getElementById('gl-canvas');

    gl = WebGLUtils.setupWebGL(document.getElementById('gl-canvas'));
    fit_canvas_to_window();

    gl.clearColor(0.0, 0.0, 0.0, 1.0);

    gl.enable(gl.DEPTH_TEST);

    program = initShaders(gl, 'default-vertex', 'default-fragment');

    gl.useProgram(program);

    mModelViewLoc = gl.getUniformLocation(program, "mModelView");
    mProjectionLoc = gl.getUniformLocation(program, "mProjection");

    sphereInit(gl);

    render();
}

const PLANET_SCALE = 1;
const ORBIT_SCALE = 1;

const SUN_DIAMETER = 1391900;
const SUN_DAY = 24.47; // At the equator. The poles are slower as the sun is gaseous

const MERCURY_DIAMETER = 4866 * PLANET_SCALE;
const MERCURY_ORBIT = 57950000 * ORBIT_SCALE;
const MERCURY_YEAR = 87.97;
const MERCURY_DAY = 58.646;

const VENUS_DIAMETER = 12106 * PLANET_SCALE;
const VENUS_ORBIT = 108110000 * ORBIT_SCALE;
const VENUS_YEAR = 224.70;
const VENUS_DAY = 243.018;

const EARTH_DIAMETER = 12742 * PLANET_SCALE;
const EARTH_ORBIT = 149570000 * ORBIT_SCALE;
const EARTH_YEAR = 365.26;
const EARTH_DAY = 0.99726968;

const MOON_DIAMETER = 3474 * PLANET_SCALE;
const MOON_ORBIT = 363396 * ORBIT_SCALE;
const MOON_YEAR = 28;
const MOON_DAY = 0;

const VP_DISTANCE = EARTH_ORBIT;


function sun() {
    multRotationY(time * SUN_DAY / 360);
    var scaleSun = [60 * SUN_DIAMETER, 60 * SUN_DIAMETER, 60 * SUN_DIAMETER];
    multScale(scaleSun);
    //console.log("SOL  ");
    //  multTranslation([0,0,0]); como a translacao vai ser (0,0,0), nao precisamos de por

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    sphereDrawWireFrame(gl, program);
}

function mercurio() {
    //console.log("MERCURIO " );
    multRotationY(time * 36 / MERCURY_YEAR);

    var t = [MERCURY_ORBIT, 0, 0];
    multTranslation(t);

    multRotationY(time * MERCURY_DAY / MERCURY_YEAR);
    var scaleMercurio = [1500 * MERCURY_DIAMETER, 1500 * MERCURY_DIAMETER, 1500 * MERCURY_DIAMETER];
    multScale(scaleMercurio);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    sphereDrawFilled(gl, program);
}

function venus() {
    multRotationY(time * 36 / VENUS_YEAR);


    var t = [VENUS_ORBIT, 0, 0];
    multTranslation(t);

    multRotationY(time * VENUS_DAY / VENUS_YEAR);

    var scaleVenus = [1500 * VENUS_DIAMETER, 1500 * VENUS_DIAMETER, 1500 * VENUS_DIAMETER];
    multScale(scaleVenus);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    sphereDrawFilled(gl, program);

}
function earth() {
    // multRotationY(time*360/EARTH_YEAR);

    // var t = [EARTH_ORBIT, 0, 0];
    // multTranslation(t);

    multRotationY(time * EARTH_DAY / EARTH_YEAR);

    var scaleEarth = [1500 * EARTH_DIAMETER, 1500 * EARTH_DIAMETER, 1500 * EARTH_DIAMETER];
    multScale(scaleEarth);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    sphereDrawWireFrame(gl, program);

}

function moon() {

    multRotationY(time * 36 / MOON_YEAR);

    var t = [MOON_ORBIT * 35 + EARTH_DIAMETER, 0, 0];
    multTranslation(t);

    // multRotationY(time*1000*MOON_DAY/MOON_YEAR); nao ha rotacao na lua em torno de si propria

    var scaleMoon = [1000 * MOON_DIAMETER, 1000 * MOON_DIAMETER, 1000 * MOON_DIAMETER];
    multScale(scaleMoon);

    gl.uniformMatrix4fv(mModelViewLoc, false, flatten(modelView));
    sphereDrawWireFrame(gl, program);


}


function render() {
    requestAnimationFrame(render);

    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

    var projection = ortho(-VP_DISTANCE * aspect, VP_DISTANCE * aspect, -VP_DISTANCE, VP_DISTANCE, -3 * VP_DISTANCE, 3 * VP_DISTANCE);

    gl.uniformMatrix4fv(mProjectionLoc, false, flatten(projection));

    modelView = lookAt([0, VP_DISTANCE, VP_DISTANCE], [0, 0, 0], [0, 1, 0]);

    pushMatrix();
    sun();
    popMatrix();
    pushMatrix();
    mercurio();
    popMatrix();
    pushMatrix();
    venus();

    popMatrix();
    pushMatrix();
    multRotationY(time * 36 / EARTH_YEAR);

    var t = [EARTH_ORBIT, 0, 0];
    multTranslation(t);

    pushMatrix();
    earth();

    popMatrix();
    pushMatrix();
    moon();

    popMatrix();
    popMatrix();


    time++;

}