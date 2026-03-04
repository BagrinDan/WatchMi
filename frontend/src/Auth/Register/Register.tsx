import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Register.css";

function Register() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [msg, setMsg] = useState("");
  const navigate = useNavigate();

  const handleRegister = async () => {
    if (!username || !password || !confirmPassword) {
      setMsg("Заполни все поля, это не квест");
      return;
    }

    try {
      await axios.post("http://localhost:8080/api/auth/register", {
        username,
        password,
        confirmPassword
      });
      navigate("/login");
    } catch (err) {
      if (axios.isAxiosError(err)) {
        setMsg(err.response?.data || "Ошибка сервера");
      } else {
        setMsg("Неизвестная ошибка");
      }
    }
  };

  return (
    <div className="register-page">
      <div className="register-card">
        <div className="register-header">
          <h1>Регистрация</h1>
          <p>Создай свой аккаунт</p>
        </div>

        {msg && <div className="register-message error">{msg}</div>}

        <div className="register-form">
          <div className="form-group">
            <label>Username</label>
            <input
              type="text"
              className="form-input"
              placeholder="Username"
              value={username}
              onChange={e => setUsername(e.target.value)}
            />
          </div>

          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              className="form-input"
              placeholder="Password"
              value={password}
              onChange={e => setPassword(e.target.value)}
            />
          </div>

          <div className="form-group">
            <label>Confirm Password</label>
            <input
              type="password"
              className="form-input"
              placeholder="Confirm Password"
              value={confirmPassword}
              onChange={e => setConfirmPassword(e.target.value)}
            />
          </div>

          <button className="register-button" onClick={handleRegister}>
            Зарегистрироваться
          </button>
        </div>

        <div className="register-footer">
          Уже есть аккаунт? <a href="/login" className="login-link">Войти</a>
        </div>
      </div>
    </div>
  );
}

export default Register;
