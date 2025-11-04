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