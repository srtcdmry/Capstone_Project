package com.dgpays.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class LoginResource {
    @SerializedName("address")

    private Address address;
    @SerializedName("id")

    private Integer id;
    @SerializedName("email")

    private String email;
    @SerializedName("username")

    private String username;
    @SerializedName("password")

    private String password;
    @SerializedName("name")

    private Name name;
    @SerializedName("phone")

    private String phone;
    @SerializedName("__v")

    private Integer v;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public class Geolocation {

        @SerializedName("lat")

        private String lat;
        @SerializedName("long")

        private String _long;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLong() {
            return _long;
        }

        public void setLong(String _long) {
            this._long = _long;
        }
    }

    public class Name {

        @SerializedName("firstname")

        private String firstname;
        @SerializedName("lastname")

        private String lastname;

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }
    }

    public class Address {

        @SerializedName("geolocation")

        private Geolocation geolocation;
        @SerializedName("city")

        private String city;
        @SerializedName("street")

        private String street;
        @SerializedName("number")

        private Integer number;
        @SerializedName("zipcode")

        private String zipcode;

        public Geolocation getGeolocation() {
            return geolocation;
        }

        public void setGeolocation(Geolocation geolocation) {
            this.geolocation = geolocation;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

    }

}





