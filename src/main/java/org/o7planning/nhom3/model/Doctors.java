package org.o7planning.nhom3.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Doctors implements Serializable {
    private String message;
    private DoctorData data;

    public Doctors(String message, DoctorData data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DoctorData getData() {
        return data;
    }

    public void setData(DoctorData data) {
        this.data = data;
    }

    public class DoctorData implements Serializable {
        private List<DoctorRecords> records = null;
        private DoctorPagination pagination;

        public DoctorData(List<DoctorRecords> records, DoctorPagination pagination) {
            this.records = records;
            this.pagination = pagination;
        }

        public List<DoctorRecords> getRecords() {
            return records;
        }

        public void setRecords(List<DoctorRecords> records) {
            this.records = records;
        }

        public DoctorPagination getPagination() {
            return pagination;
        }

        public void setPagination(DoctorPagination pagination) {
            this.pagination = pagination;
        }

        public class DoctorRecords implements Serializable {
            private String _id;
            private String fullName;
            private String email;
            private String avatar;
            private String role;
            private String descriptions;
            private String specialisation;
            private String facility;
            private String createdAt;
            private String updatedAt;
            private DoctorUnavailableTime unavailableTime;

            public DoctorRecords(String _id, String fullName, String email, String avatar, String role, String descriptions, String specialisation, String facility, String createdAt, String updatedAt, DoctorUnavailableTime unavailableTime) {
                this._id = _id;
                this.fullName = fullName;
                this.email = email;
                this.avatar = avatar;
                this.role = role;
                this.descriptions = descriptions;
                this.specialisation = specialisation;
                this.facility = facility;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
                this.unavailableTime = unavailableTime;
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

            public String getDescriptions() {
                return descriptions;
            }

            public void setDescriptions(String descriptions) {
                this.descriptions = descriptions;
            }

            public String getSpecialisation() {
                return specialisation;
            }

            public void setSpecialisation(String specialisation) {
                this.specialisation = specialisation;
            }

            public String getFacility() {
                return facility;
            }

            public void setFacility(String facility) {
                this.facility = facility;
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

            public DoctorUnavailableTime getUnavailableTime() {
                return unavailableTime;
            }

            public void setUnavailableTime(DoctorUnavailableTime unavailableTime) {
                this.unavailableTime = unavailableTime;
            }

            public class DoctorUnavailableTime implements Serializable {
                private Map<String, List<String>> additionalProperties = new HashMap<String, List<String>>();

                public DoctorUnavailableTime(Map<String, List<String>> additionalProperties) {
                    this.additionalProperties = additionalProperties;
                }

                public Map<String, List<String>> getAdditionalProperties() {
                    return additionalProperties;
                }

                public void setAdditionalProperties(Map<String, List<String>> additionalProperties) {
                    this.additionalProperties = additionalProperties;
                }
            }


        }
        public class DoctorPagination implements Serializable {
            private int page;
            private int limit;
            private int records;
            private int total_records;
            private int total_page;

            public DoctorPagination(int page, int limit, int records, int total_records, int total_page) {
                this.page = page;
                this.limit = limit;
                this.records = records;
                this.total_records = total_records;
                this.total_page = total_page;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public int getRecords() {
                return records;
            }

            public void setRecords(int records) {
                this.records = records;
            }

            public int getTotal_records() {
                return total_records;
            }

            public void setTotal_records(int total_records) {
                this.total_records = total_records;
            }

            public int getTotal_page() {
                return total_page;
            }

            public void setTotal_page(int total_page) {
                this.total_page = total_page;
            }
        }
    }
}
