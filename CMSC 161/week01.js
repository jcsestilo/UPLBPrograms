const canvas = document.querySelector("#output");
const gl = canvas.getContext("webgl2");

const createShader = (gl, type, sourceCode) => {
    let shader = gl.createShader(type);
    gl.shaderSource(shader, sourceCode);
    gl.compileShader(shader);

    return shader;
};

const vertexShaderSource = document.querySelector("#vertex-shader").text;
const fragmentShaderSource = 
document.querySelector('#fragment-shader').text;

//function call to createShader; the return value is captured by vertexShader and fragmentShader
const vertexShader = createShader(gl, gl.VERTEX_SHADER, vertexShaderSource);
const fragmentShader = createShader(
    gl, 
    gl.FRAGMENT_SHADER, 
    fragmentShaderSource);

let program = gl.createProgram();

gl.attachShader(program, vertexShader);
gl.attachShader(program, fragmentShader);

gl.linkProgram(program);

gl.useProgram(program);

// Declaration of pointers to the attributes
const aPositionPointer = gl.getAttribLocation(program, 'a_position');
const aPointSizePointer = gl.getAttribLocation(program, 'a_point_size');

let myXYArrays = [
    [-0.2, 0.50], // center top ng aso
    [-0.3, 0.50], // left
    [-0.4, 0.47],
    [-0.5, 0.40],
    [-0.6, 0.30],
    [-0.63, 0.22],
    [-0.63, 0.10],
    [-0.6, 0.0],
    [-0.58, -0.10],
    [-0.52, -0.20],
    [-0.48, -0.30],
    [-0.42, -0.35],
    [-0.3, -0.38],
    [-0.2, -0.40],
    [-0.1, 0.50], // right
    [-0.0, 0.50],
    [0.08, 0.47],
    [0.14, 0.40],
    [0.20, 0.30],
    [0.24, 0.22],
]

for(let i=0; i<myXYArrays.length; i++){
    gl.vertexAttrib4f(aPositionPointer, myXYArrays[i][0], myXYArrays[i][1], 0.0, 1.0); // x=0, y=0, z=0, w=1
    gl.vertexAttrib1f(aPointSizePointer, 10.0);
    gl.drawArrays(gl.POINTS, 0, 1);
}