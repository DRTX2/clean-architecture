import React, { useEffect, useRef, useState } from "react";
import "./App.css";
import Button from "./components/Button";

function App() {
  // 🧱 STATE: Datos internos que cambian con el tiempo
  // useState: Hook que permite crear un estado interno en un componente funcional
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const [counter, setCounter] = useState<number>(0);

  const [nombres, setNombres] = useState<string[]>(["Ana", "Luis", "Marta"]);
  // useRef: Hook que permite crear una referencia mutable que persiste durante el ciclo de vida del componente
  const input = useRef<HTMLInputElement>(null);

  const changeState = () => {
    setIsOpen(!isOpen);
    setCounter(counter + 1);
    input?.current?.focus();
  };

  useEffect(() => {// no monta, desmonta sino que se ejecuta cada vez que cambia el estado de counter
    console.log("Se dio un click mas");

    const interval = setInterval(() => {
      console.log("Intervalo corriendo");
    }, 1000);

    return () => {//se usa para limpiar efectos secundarios, no para desmontar componentes.
      console.log("Limpieza: Se va a ejecutar un nuevo efecto o se desmontó el componente");
      clearInterval(interval);
      console.log("Intervalo limpiado");
    };
  }, [counter]);

  return (
    <div>
      <form>
        <input type="text" ref={input} placeholder="Escribe tu nombre" />
      </form>
      <Button changeState={changeState}></Button>

      {isOpen && <div>Se dieron {counter} clicks</div>}

      <h3>Lista de nombres:</h3>
      <ul>
        {nombres.map((nombre, index) => (
          <li key={index}>{nombre}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;
