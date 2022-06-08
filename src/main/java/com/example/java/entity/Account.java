package com.example.java.entity;

import com.example.java.entity.myenum.AccountStatus;

public class Account{
        private int id;
        private String username;
        private String email;
        private String password;
        private String confirmPassword;
        private String fullName;
        private int roleId;
        private AccountStatus status;

        public Account(int id, String username, String email, String password, String confirmPassword, String fullName, int roleId, AccountStatus status) {
                this.id = id;
                this.username = username;
                this.email = email;
                this.password = password;
                this.confirmPassword = confirmPassword;
                this.fullName = fullName;
                this.roleId = roleId;
                this.status = status;
        }

        public Account() {
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getConfirmPassword() {
                return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
                this.confirmPassword = confirmPassword;
        }

        public String getFullName() {
                return fullName;
        }

        public void setFullName(String fullName) {
                this.fullName = fullName;
        }

        public int getRoleId() {
                return roleId;
        }

        public void setRoleId(int roleId) {
                this.roleId = roleId;
        }

        public AccountStatus getStatus() {
                return status;
        }

        public void setStatus(AccountStatus status) {
                this.status = status;
        }
}
