import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";



import Header from "./Components/Header/Header"
import "./index.css";



function Home() {
  const navigate = useNavigate();
  
  const [movies, setMovies] = useState<string[]>([]);
  const [newMovie, setNewMovie] = useState("");
  const [messages, setMessages] = useState<{ text: string; isBot: boolean }[]>([  ]);
  const [userInput, setUserInput] = useState("");
  const [isAuthenticated, setIsAuthenticated] = useState(false);


  useEffect(() => {
    const token = localStorage.getItem("token");

    if(token){
      setIsAuthenticated(true);
    }
    console.log(token)
  }, []);


  const handleLogout = () => {
    localStorage.removeItem("token"); // -- TODO: Поменять на HttpOnly
    setIsAuthenticated(false);
    navigate("/login")
  }


  const handleAddMovie = () => {
    if (!newMovie.trim()) {
      alert("Enter movie name");
      return;
    }
    if (movies.includes(newMovie.trim())) {
      alert("Этот фильм уже есть в списке");
      return;
    }
    
    const trimmedMovie = newMovie.trim();
    setMovies([...movies, trimmedMovie]);
    setNewMovie("");
  };
  

  const handleSendMessage = async () => {
    const trimmedInput = userInput.trim();
    if (!trimmedInput) return;

    // 1. Сразу добавляем сообщение пользователя в интерфейс
    const userMsg = { text: trimmedInput, isBot: false };
    setMessages((prev) => [...prev, userMsg]);
    setUserInput("");

    // 2. Добавляем временное пустое сообщение от бота
    setMessages((prev) => [...prev, { text: "Печатает...", isBot: true }]);

    try {
      // 3. Дергаем Spring Boot эндпоинт

      const response = await fetch(`/api/model/answer?message=${encodeURIComponent(trimmedInput)}`);
      
      if (!response.ok) throw new Error("Ошибка связи с сервером");
      
      const botText = await response.text(); // Получаем ответ от Spring

      // 4. Заменяем ("Печатает...") на реальный ответ
      setMessages((prev) => [
        ...prev.slice(0, -1), 
        { text: botText, isBot: true }
      ]);

    } catch (error) {
      console.error("Ошибка:", error);
      setMessages((prev) => [
        ...prev.slice(0, -1), 
        { text: "AI Agent error!", isBot: true }
      ]);
    }
  };

  const handleKeyPress = (e: React.KeyboardEvent) => {
    if (e.key === 'Enter' && !e.shiftKey) {
      e.preventDefault();
      handleSendMessage();
    }
  };

  return (
    <div className="container">
      {/* Левая панель (Список фильмов) */}
      <div className="sidebar">
        <div className="sidebar-header">
          <h2>🎬 My Films</h2>
          <p className="subtitle">Your film collections</p>
        </div>
        
        <div className="add-movie">
          <input 
            type="text" 
            value={newMovie}
            onChange={(e) => setNewMovie(e.target.value)}
            onKeyDown={(e) => e.key === 'Enter' && handleAddMovie()}
            placeholder="Enter movie name..." 
            className="movie-input"
          />
          <button 
            onClick={handleAddMovie}
            className="add-button"
            disabled={!newMovie.trim()}
          >
            ➕ Add
          </button>
        </div>
      </div>

      {/* Правая часть: чат */}
      <div className="chat-container">
        {/* Header */}
        <Header 
          isAuthenticated={isAuthenticated} 
          onLogout={handleLogout} 
        />

        {/* Chat output*/}
        <main className="chat-main">
          <div className="chat-output">
            {messages.map((msg, index) => (
              <div 
                key={index} 
                className={`message ${msg.isBot ? 'bot' : 'user'}`}
              >
                <div className="message-sender">
                  {msg.isBot ? 'MI:' : 'Вы:'}
                </div>
                <div className="message-content">
                  {msg.text}
                </div>
              </div>
            ))}
          </div>
        </main>

        {/* Chat input*/}
        <footer className="chat-footer">
          <div className="input-container">
            <input 
              type="text" 
              value={userInput}
              onChange={(e) => setUserInput(e.target.value)}
              onKeyDown={handleKeyPress}
              placeholder="Let's start!" 
              className="chat-input"
            />
            <button 
              onClick={handleSendMessage}
              className="send-button"
              disabled={!userInput.trim()}
            >
              *Pimp*
            </button>
          </div>
        </footer>
      </div>
    </div>
  );
}

export default Home;