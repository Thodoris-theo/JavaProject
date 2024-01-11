/*
 Spyros Kanakopoylos 3212018071 
   Thodoris Theodorellis 3212018061
 */
package project;
import java.util.List;


public class Movie {
    private int code;
    private String title;
    private int year;
    private List<String> actors;
    private int duration;
    private List<Ticket> tickets;

    public Movie(int code, String title, int year, List<String> actors, int duration) {
        this.code = code;
        this.title = title;
        this.year = year;
        this.actors = actors;
        this.duration = duration;
    }


    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public List<String> getActors() {
        return actors;
    }

    public int getDuration() {
        return duration;
    }

  
    public void setCode(int code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
          public void addTicket(Ticket ticket) {
        this.tickets.add(ticket); 
    }

    public List<Ticket> getTickets() {
        return tickets; 
    }
}