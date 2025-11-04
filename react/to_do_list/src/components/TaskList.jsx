import React from "react";
import TodoItem from "./ToDoItem.jsx";
function TaksList({tasks,editTask,deleteTask}){

    return(
    //      <div className="w-80 space-y-2">
    //   {tasks.length === 0 ? (
    //     <p className="text-gray-600 text-center">No tasks added yet...</p>
    //   ) : (
    //     tasks.map((task) => (
    //       <TodoItem
    //         key={task.id}
    //         task={task}
    //         editTask={editTask}
    //         deleteTask={deleteTask}
    //       />
    //     ))
    //   )}
    // </div>


    <div>
      {tasks.length === 0 ? (
        <p className="text-center text-muted">No tasks added yet...</p>
      ) : (
        <ul className="list-group">
          {tasks.map((task) => (
            <TodoItem
              key={task.id}
              task={task}
              editTask={editTask}
              deleteTask={deleteTask}
            />
          ))}
        </ul>
      )}
    </div>
    );
}

export default TaksList;