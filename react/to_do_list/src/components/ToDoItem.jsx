import { useState } from "react";

function TodoItem({task,editTask,deleteTask}){

    const [isEditing, setIsEditing] = useState(false);
    const [newText, setNewText] = useState(task.text);

    const handleEdit = ()=>{

        if(isEditing){

            editTask(task.id,newText);
        } 
        setIsEditing(!isEditing);
    };

    return(
    //     <div className="flex justify-between items-center bg-white p-3 rounded-lg shadow">
    //   {isEditing ? (
    //     <input
    //       value={newText}
    //       onChange={(e) => setNewText(e.target.value)}
    //       className="border border-gray-300 p-1 rounded w-3/4"
    //     />
    //   ) : (
    //     <span>{task.text}</span>
    //   )}

    //   <div className="flex gap-2">
    //     <button onClick={handleEdit} className="text-blue-500 font-semibold">
    //       {isEditing ? "Save" : "Edit"}
    //     </button>
    //     <button onClick={() => deleteTask(task.id)} className="text-red-500 font-semibold">
    //       Delete
    //     </button>
    //   </div>
    // </div>

     <li className="list-group-item d-flex justify-content-between align-items-center">
      {isEditing ? (
        <input
          value={newText}
          onChange={(e) => setNewText(e.target.value)}
          className="form-control me-2"
          style={{ width: "70%" }}
        />
      ) : (
        <span>{task.text}</span>
      )}
      <div className="d-flex gap-2">
        <button onClick={handleEdit} className="btn btn-sm btn-outline-primary">
          {isEditing ? "Save" : "Edit"}
        </button>
        <button onClick={() => deleteTask(task.id)} className="btn btn-sm btn-outline-danger">
          Delete
        </button>
      </div>
    </li>
    )
}

export default TodoItem;