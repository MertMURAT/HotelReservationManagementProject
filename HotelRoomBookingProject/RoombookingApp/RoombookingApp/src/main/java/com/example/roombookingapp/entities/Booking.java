package com.example.roombookingapp.entities;

import javax.persistence.*;

import com.example.roombookingapp.command.Layout;

import java.sql.Time;
import java.sql.Date;


@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Room room;

    @ManyToOne
    private User user;

    private Layout layout;

    private String title;
    private Date date;
    private Time startTime;
    private Time endTime;
    private Integer participants;
    @Enumerated(EnumType.STRING)
    private  BookingStatus status;

    public Booking() {
    }

    public Booking(Room room, User user, Layout layout, String title, Date date, Time startTime, Time endTime, Integer participants, BookingStatus status) {
        this.room = room;
        this.user = user;
        this.layout = layout;
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
        this.status=status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public Integer getDisplayDay(){
        return this.date.toLocalDate().getDayOfMonth();
    }

    public String getDisplayMonth(){
        return this.date.toLocalDate().getMonth().toString();
    }

    public String getDisplayDayOfWeek(){
        return this.date.toLocalDate().getDayOfWeek().toString();
    }

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}
    
    

}
