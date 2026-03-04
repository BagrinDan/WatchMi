import { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import "./Login.css"; 

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [msg, setMsg] = useState("");
  const [loading, setLoading] = useState(false); 
  const navigate = useNavigate();

  const handleLogin = async () => {
    setLoading(true);
    try {
      const res = await axios.post("http://localhost:8080/api/auth/login", {
        username,
        password,
      });

      if (res.data.token) {
        localStorage.setItem("token", res.data.token);
      }
      
      setMsg("success"); 
      navigate("/");
    } catch (err: any) {
      setMsg(err.response?.data?.message || "Ошибка авторизации");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-page">
      <div className="login-card">
        <header className="login-header">
          <h1>Login</h1>
          <p>Welcome to WatchWithMI</p>
        </header>

        { }
        {msg && msg !== "success" && (
          <div className="login-message error">{msg}</div>
        )}

        <form
          className="login-form"
          onSubmit={(e) => {
            e.preventDefault();
            handleLogin();
          }}
        >
          <div className="form-group">
            <label>Username</label>
            <input
              className="form-input"
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              placeholder="Ваш никнейм"
              required
            />
          </div>

          <div className="form-group">
            <label>Password</label>
            <input
              className="form-input"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="••••••••"
              required
            />
          </div>

          <div className="form-options">
            <label className="remember-me">
              <input type="checkbox" /> Remember me
            </label>
            <a href="#" className="forgot-password">Forget password?</a>
          </div>

          <button 
            type="submit" 
            className="login-button" 
            disabled={loading}
          >
            {loading ? <span className="login-loading"></span> : "Войти"}
          </button>

          <button
            type="button"
            className="login-button"
            style={{ backgroundColor: "#333", marginTop: "5px" }}
            onClick={() => navigate("/")}
          >
            Назад
          </button>
        </form>

        <footer className="login-footer">
          <p>
            No account?{" "}
            <Link to="/register" className="register-link">
              Register!
            </Link>
          </p>
        </footer>
      </div>
    </div>
  );
}

export default Login;