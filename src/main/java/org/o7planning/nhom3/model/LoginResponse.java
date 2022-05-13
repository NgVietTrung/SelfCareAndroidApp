package org.o7planning.nhom3.model;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private String message;
    private LoginData data = null;

    public LoginResponse(String message) {
        this.message = message;
    }

    public LoginResponse(String message, LoginData data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }

    public class LoginData implements Serializable {
        private User user;
        private String accessToken;
        private String refreshToken;

        public LoginData(User user, String accessToken, String refreshToken) {
            this.user = user;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public class User implements Serializable {
            private String _id;
            private String fullName;
            private String email;
            private String avatar;
            private String role;
            private String createdAt;
            private String updatedAt;

            public User(String _id, String fullName, String email, String avatar, String role, String createdAt, String updatedAt) {
                this._id = _id;
                this.fullName = fullName;
                this.email = email;
                this.avatar = avatar;
                this.role = role;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }
    }
}
