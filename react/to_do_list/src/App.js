import { useEffect, useState } from 'react';
import './App.css';
import Form from './components/Form.jsx';
import TaskList from './components/TaskList.jsx';

function App() {

  const [tasks,setTasks] = useState(()=>{
    const savedTasks = localStorage.getItem("tasks");
    return savedTasks ? JSON.parse(savedTasks) : [];
  })

  useEffect(()=>{

    localStorage.setItem("tasks",JSON.stringify(tasks));
  },[tasks]);

  const addTask = (taskText) =>{

    const newTask = {id: Date.now(), text: taskText};
    setTasks([...tasks, newTask]);
  };

  const deleteTask = (id)=>{
    const taksToDelete = tasks.find((task)=> task.id === id);
    const confirmDelete = window.confirm(
      `Are you sure You want to delete the task: ${taksToDelete.text}`
    )

    if(confirmDelete){
      setTasks(tasks.filter((task)=> task.id !==id));
    }
    
  };

  const editTask = (id, updatedText) => {
    setTasks(tasks.map((task) => (task.id === id ? { ...task, text: updatedText } : task)));
  };

  return (

    <div className="min-vh-100 bg-light d-flex flex-column align-items-center p-5">
      <div className="card shadow-lg p-4" style={{ width: "28rem" }}>
        <h1 className="text-center mb-4 text-primary">📝 To-Do List</h1>
        <Form addTask={addTask} />
        <TaskList tasks={tasks} editTask={editTask} deleteTask={deleteTask} />
      </div>
    </div>
  );
}

export default App;
