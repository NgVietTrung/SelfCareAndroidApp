package org.o7planning.nhom3.model;

import java.io.Serializable;

public class Assignment implements Serializable {
    private String message;
    private AssignmentData data;

    public Assignment(String message, AssignmentData data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AssignmentData getData() {
        return data;
    }

    public void setData(AssignmentData data) {
        this.data = data;
    }

    public class AssignmentData implements Serializable {
        private AssignmentRecord record;

        public AssignmentData(AssignmentRecord record) {
            this.record = record;
        }

        public AssignmentRecord getRecord() {
            return record;
        }

        public void setRecord(AssignmentRecord record) {
            this.record = record;
        }

        public class AssignmentRecord implements Serializable {
            private String patient;
            private String doctor;
            private String notes;
            private String status;
            private AssignmentTime assignmentTime;
            private String _id;
            private String createdAt;
            private String updatedAt;

            public AssignmentRecord(String patient, String doctor, String notes, String status, AssignmentTime assignmentTime, String _id, String createdAt, String updatedAt) {
                this.patient = patient;
                this.doctor = doctor;
                this.notes = notes;
                this.status = status;
                this.assignmentTime = assignmentTime;
                this._id = _id;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
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

            public AssignmentTime getAssignmentTime() {
                return assignmentTime;
            }

            public void setAssignmentTime(AssignmentTime assignmentTime) {
                this.assignmentTime = assignmentTime;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
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

            public class AssignmentTime implements Serializable {
                private String date;
                private String time;

                public AssignmentTime(String date, String time) {
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
