import { useState } from "react";


function Form({addTask}){

    const [input,setInput] = useState("");

    const handleSubmit = (e)=>{

        e.preventDefault();

        if(input.trim()==="") return;

        addTask(input);
        setInput("");
    }

    return(

    //     <form onSubmit={handleSubmit} className="mb-6 flex gap-2">
    //   <input
    //     type="text"
    //     placeholder="Enter a task..."
    //     value={input}
    //     onChange={(e) => setInput(e.target.value)}
    //     className="border border-gray-400 rounded-lg p-2 w-64"
    //   />
    //   <button type="submit" className="btn btn-primary">
    //     Add
    //   </button>
    // </form>


    <form onSubmit={handleSubmit} className="mb-4 d-flex">
      <input
        type="text"
        placeholder="Enter a task..."
        value={input}
        onChange={(e) => setInput(e.target.value)}
        className="form-control me-2"
      />
      <button type="submit" className="btn btn-primary">
        Add
      </button>
    </form>
    )
}

export default Form;