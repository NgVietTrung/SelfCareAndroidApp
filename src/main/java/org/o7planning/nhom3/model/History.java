package org.o7planning.nhom3.model;

import java.io.Serializable;
import java.util.List;

public class History implements Serializable {
    private String message;
    private HistoryData data;

    public History(String message, HistoryData data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HistoryData getData() {
        return data;
    }

    public void setData(HistoryData data) {
        this.data = data;
    }

    public class HistoryData implements Serializable {
        private HistoryUser user;

        public HistoryData(HistoryUser user) {
            this.user = user;
        }

        public HistoryUser getUser() {
            return user;
        }

        public void setUser(HistoryUser user) {
            this.user = user;
        }

        public class HistoryUser implements Serializable {
            private String _id;
            private String fullName;
            private String email;
            private String avatar;
            private String role;
            private String createdAt;
            private String updatedAt;
            private List<HistoryAssignments> patientAssignments;

            public HistoryUser(String _id, String fullName, String email, String avatar, String role, String createdAt, String updatedAt, List<HistoryAssignments> patientAssignments) {
                this._id = _id;
                this.fullName = fullName;
                this.email = email;
                this.avatar = avatar;
                this.role = role;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
                this.patientAssignments = patientAssignments;
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

            public List<HistoryAssignments> getPatientAssignments() {
                return patientAssignments;
            }

            public void setPatientAssignments(List<HistoryAssignments> patientAssignments) {
                this.patientAssignments = patientAssignments;
            }

            public class HistoryAssignments implements Serializable {
                private HistoryAssignmentTime assignmentTime;
                private String _id;
                private String patient;
                private String doctor;
                private String notes;
                private String status;
                private String createdAt;
                private String updatedAt;

                public HistoryAssignments(HistoryAssignmentTime assignmentTime, String _id, String patient, String doctor, String notes, String status, String createdAt, String updatedAt) {
                    this.assignmentTime = assignmentTime;
                    this._id = _id;
                    this.patient = patient;
                    this.doctor = doctor;
                    this.notes = notes;
                    this.status = status;
                    this.createdAt = createdAt;
                    this.updatedAt = updatedAt;
                }

                public HistoryAssignmentTime getAssignmentTime() {
                    return assignmentTime;
                }

                public void setAssignmentTime(HistoryAssignmentTime assignmentTime) {
                    this.assignmentTime = assignmentTime;
                }

                public String get_id() {
                    return _id;
                }

                public void set_id(String _id) {
                    this._id = _id;
                }

                public String getPatient() {
                    return patient;
                }

                public void setPatient(String patient) {
                    this.patient = patient;
                }

                public String getDoctor() {
                    return doctor;
                }

                public void setDoctor(String doctor) {
                    this.doctor = doctor;
                }

                public String getNotes() {
                    return notes;
                }

                public void setNotes(String notes) {
                    this.notes = notes;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
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

                public class HistoryAssignmentTime implements Serializable {
                    private String date;
                    private String time;

                    public HistoryAssignmentTime(String date, String time) {
                        this.date = date;
                        this.time = time;
                    }

                    public String getDate() {
                        return date;
                    }

                    public void setDate(String date) {
                        this.date = date;
                    }

                    public String getTime() {
                        return time;
                    }

                    public void setTime(String time) {
                        this.time = time;
                    }
                }
            }
        }
    }
}
