// Instalación y compilado

// npm install -g typescript

// iniciar un proyecto un proyecto TS: tsc --init -> tsconfig.json(tsx,es6,es2025, paths y estructuras de carpetas)

// tsc archivo.ts

// Básicos
let nombre: string = "Pedro";

nombre = "juan";

const edad: number = 22;
const esDev: boolean = true;
const habilidades: string[] = ["Java", "PHP", "TS"];

console.log(`Nombre: ${nombre}`);
console.log(`Edad: ${edad}`);
console.log(`¿Es desarrollador?: ${esDev}`);
console.log(`Habilidades: ${habilidades.join(", ")}`);

// Tipos inferidos
const framework:string = "Spring";
console.log(`Framework favorito: ${framework}, ${edad}`);

// Funciones
function sumar(a: number, b: number): number {
  return a + b;
}

const resultado = sumar(10, 5);
console.log(`Resultado de la suma: ${resultado}`);

const saludar = (nombre: string): string => `Hola ${nombre}`;

const restar = (a:number, b:number):number=> a-b;

console.log(saludar("Mundo"));

// Interfaces y tipos
interface Usuario { 
    // UsuarioAdministrador, UsuarioComun
  nombre: string;
  edad: number;
  direccion: string;
  activo?: boolean;
}

const user: Usuario = 
    {
        nombre: "Josué",
        edad: 22,
    };

console.log(`Usuario: ${user.nombre}, Edad: ${user.edad}`);


type Estado = "cargando" | "exito" | "error";
type Categoria = "a" | "b" | "c" | number;
let estadoActual: Estado = "cargando";

console.log(`Estado actual: ${estadoActual}`);
estadoActual = "exito"; // cambio válido
console.log(`Estado nuevo: ${estadoActual}`);

function imprimir(id: string | number) {
  console.log("ID:", id);
}

imprimir(123);
imprimir("abc-456");

// Clases
class Persona {
  constructor(public nombre: string, private edad: number) {}

  saludar(): string {
    return `Hola, soy ${this.nombre}`;
  }

  getEdad(): number {
    return this.edad;
  }
}

class Userr{
    constructor(public a:string, private b:number){}
    mostrarA():string{
        return "Esto es A: "+this.a;
    }
    mostrar2B():number{
        return this.b*2;
    }

}

const persona = new Persona("Lucía", 30);
console.log(persona.saludar());
console.log(`Edad privada: ${persona.getEdad()}`);

const myUser= new Userr("aaa", 10);
console.log(myUser.a);
console.log(myUser.mostrar2B);


/*

🤔 ¿Cuándo usar .ts y cuándo .tsx?
.ts → archivos comunes de lógica, tipos, clases, funciones.

.tsx → archivos que contienen JSX (React).

*/
