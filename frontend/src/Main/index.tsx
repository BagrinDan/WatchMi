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
    localStorage.removeItem("token"); // -- TODO: Поменять в HttpOnly
    setIsAuthenticated(false);
    navigate("/login")
  }

  const handleAddMovie = () => {
    if (!newMovie.trim()) {
      alert("Пожалуйста, введите название фильма");
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

  const handleSendMessage = () => {
    const trimmedInput = userInput.trim();
    if (!trimmedInput) return;
    
    // Добавляем сообщение пользователя
    const updatedMessages = [...messages, { text: trimmedInput, isBot: false }];
    
    // Генерируем более осмысленный ответ (можно заменить на реальный AI)
    const botResponse = generateBotResponse(trimmedInput);
    
    setMessages([...updatedMessages, { text: `MI: ${botResponse}`, isBot: true }]);
    setUserInput("");
  };

  // -- TODO: Инкапсулировать в отдельную директорию 
  const generateBotResponse = (input: string): string => {
    const lowerInput = input.toLowerCase();
    
    if (lowerInput.includes("привет") || lowerInput.includes("hello")) {
      return "Привет! Рад видеть вас! Какой фильм хотите обсудить?";
    }
    
    if (lowerInput.includes("фильм") && movies.length > 0) {
      const randomMovie = movies[Math.floor(Math.random() * movies.length)];
      return `Как насчёт "${randomMovie}"? Отличный фильм для обсуждения!`;
    }
    
    if (lowerInput.includes("спасибо") || lowerInput.includes("thanks")) {
      return "Всегда рад помочь! Есть ещё фильмы для обсуждения?";
    }
    
    // Базовые ответы если не найдено ключевых слов
    const responses = [
      "Интересная мысль! Что вы думаете о последних фильмах?",
      "Давайте поговорим о кино! Какой ваш любимый жанр?",
      "Отличная тема для обсуждения! Может, добавим ещё фильмов в список?",
      "Я обожаю обсуждать фильмы! Есть что-то конкретное, что хотели бы обсудить?"
    ];
    
    return responses[Math.floor(Math.random() * responses.length)];
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