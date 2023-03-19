// Jan Coleen S. Estilo
// Exercise 1 - CMSC 161

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
    [0.30, 0.10],
    [0.29, 0.0],
    [0.26, -0.10],
    [0.23, -0.20],
    [0.15, -0.30],
    [0.06, -0.35],
    [-0.05, -0.38],
    [-0.57, 0.18], // left eyes
    [-0.50, 0.24],
    [-0.40, 0.24],
    [-0.30, 0.20],
    [-0.25, 0.10],
    [-0.25, 0.05],
    [-0.35, -0.05],
    [-0.45, -0.13],
    [-0.38, 0.07],
    [-0.48, 0.05],
    [-0.40, 0.0],
    [-0.35, 0.02],
    [0.25, 0.15], // right eyes
    [0.17, 0.20], 
    [0.07, 0.22],
    [0.0, 0.19],
    [-0.07, 0.15],
    [-0.10, 0.07],
    [-0.03, -0.05],
    [0.05, -0.10],
    [0.15, -0.13],
    [0.01, 0.01],
    [0.10, 0.00],
    [0.15, 0.05],
    [0.10, 0.08],
    [0.03, 0.07],

    [-0.15, -0.20], // nose
    [-0.24, -0.20],
    [-0.28, -0.25],
    [-0.25, -0.30],
    [-0.20, -0.33],
    [-0.15, -0.30],
    [-0.12, -0.25],

    [-0.59, 0.39], // left ears
    [-0.69, 0.36],
    [-0.80, 0.30],
    [-0.85, 0.24],
    [-0.85, 0.15],
    [-0.83, 0.03],
    [-0.77, -0.05],
    [-0.70, -0.09],
    [-0.67, -0.01],
    [-0.71, 0.07],
    [-0.67, 0.14],
    [0.25, 0.40], // right ears
    [0.34, 0.38],
    [0.45, 0.36],
    [0.57, 0.30],
    [0.60, 0.23],
    [0.60, 0.16],
    [0.56, 0.07],
    [0.50, 0.00],
    [0.45, -0.07],
    [0.37, -0.02],
    [0.35, 0.07],
    [0.37, 0.15],
    [0.35, 0.23],
    [0.30, 0.25],
    [-0.2, -0.50], // left leg
    [-0.23, -0.58],
    [-0.27, -0.67],
    [-0.30, -0.75],
    [-0.37, -0.68],
    [-0.44, -0.66],
    [-0.50, -0.63],
    [-0.58, -0.66],
    [-0.63, -0.68],
    [-0.69, -0.70],
    [-0.76, -0.73],
    [-0.76, -0.80],
    [-0.72, -0.82],
    [-0.67, -0.85],
    [-0.60, -0.84],
    [-0.63, -0.80],
    [-0.60, -0.75],
    [-0.53, -0.83],
    [-0.43, -0.84],
    [-0.45, -0.80],
    [-0.44, -0.75],
    [-0.38, -0.84],
    [-0.30, -0.80],
    [-0.63, -0.60],
    [-0.66, -0.54],
    [-0.69, -0.47],
    [-0.72, -0.40],
    [-0.70, -0.32],
    [-0.65, -0.23],
    [-0.61, -0.17],

    [-0.68, -0.13], // back leg
    [-0.71, -0.18],
    [-0.73, -0.24],
    [-0.76, -0.45],
    [-0.79, -0.52],
    [-0.74, -0.53],
    [-0.83, -0.55],
    [-0.88, -0.56],
    [-0.89, -0.60],
    [-0.86, -0.67],
    [-0.80, -0.69],
    [-0.73, -0.67],
    [-0.67, -0.65],

    [0.49, -0.15], // right leg
    [0.49, -0.24],
    [0.52, -0.34],
    [0.51, -0.44],
    [0.50, -0.54],
    [0.48, -0.64],
    [0.57, -0.74],
    [0.60, -0.80],
    [0.50, -0.83],
    [0.40, -0.84],
    [0.30, -0.84],
    [0.20, -0.83],
    [0.13, -0.78],
    [0.10, -0.70],
    [0.11, -0.60],
    [0.13, -0.51],
    [0.15, -0.45],
    [0.14, -0.40],
    [0.13, -0.35],
    [0.15, -0.55],
    [0.20, -0.53],
    [0.30, -0.52],
    [0.35, -0.52],
    [0.40, -0.54],
    [0.27, -0.75],
    [0.30, -0.80],
    [0.43, -0.75],
    [0.42, -0.80],
    [0.07, -0.52], // body
    [0.00, -0.52],
    [-0.07, -0.53],
    [-0.14, -0.52],
]

for(let i=0; i<myXYArrays.length; i++){
    gl.vertexAttrib4f(aPositionPointer, myXYArrays[i][0], myXYArrays[i][1], 0.0, 1.0); // x=0, y=0, z=0, w=1
    gl.vertexAttrib1f(aPointSizePointer, 10.0);
    gl.drawArrays(gl.POINTS, 0, 1);
}