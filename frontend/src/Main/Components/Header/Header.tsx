import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Header.css";

type HeaderProps = {
  isAuthenticated: boolean;
  onLogout: () => void;
};

export default function Header({ isAuthenticated, onLogout }: HeaderProps) {
  const navigate = useNavigate();
  const [isProfileOpen, setIsProfileOpen] = useState(false);

  return (
    <header className="chat-header">
      <div className="header-content">
        <h1>🤖 WatchWithMI!</h1>
        <p className="tagline">Your personal film buddy!</p>
      </div>

      <div className="auth-buttons">
        {isAuthenticated ? (
          <div className="profile-wrapper">
            <button
              onClick={() => setIsProfileOpen(prev => !prev)}
              className="auth-button profile"
            >
              Profile
            </button>

            {isProfileOpen && (
              <div className="profile-menu">
                <button onClick={() => navigate("/profile")}>My profile</button>
                <button onClick={() => navigate("/settings")}>Settings</button>
                <button onClick={onLogout}>Log-out</button>
              </div>
            )}
          </div>
        ) : (
          <>
            <button onClick={() => navigate("/login")} className="auth-button signin">
              SignIn
            </button>
            <button onClick={() => navigate("/register")} className="auth-button signup">
              SignUp
            </button>
          </>
        )}
      </div>
    </header>
  );
}
