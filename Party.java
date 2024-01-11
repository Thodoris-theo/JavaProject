/*
 Spyros Kanakopoylos 3212018071 
   Thodoris Theodorellis 3212018061
 */
package project;


import java.util.ArrayList;

public class Party {
    private int partyUniqueCode;
    private Boolean clownOption;
    private int day;
    private int month;
    private String partyTime;
    private int kids;
    private boolean menuOption;
    private boolean cakeOption;
    private Room room;
    private static int counter = 0;
    
    public Party(Boolean clownOption, int day, String time, int month, int kids, boolean menuOption, boolean cakeOption, Room room) {
        this.clownOption = clownOption;
        this.day = day;
        this.month = month;
        this.partyTime = time;
        this.kids = kids;
        this.menuOption = menuOption;
        this.cakeOption = cakeOption;
        this.partyUniqueCode = createUniqueCode();
        this.room = room;
    }

    private int createUniqueCode() {
        counter++;
        return counter;
    }

    public int getPartyUniqueCode() {
        return partyUniqueCode;
    }

    public Boolean getClownOption() {
        return clownOption;
    }

    public String getPartyTime() {
        return partyTime;
    }

    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public String getRoom() {
        return room.getRoomName();
    }

    public int getKids() {
        return kids;
    }

    public boolean getMenuOption() {
        return menuOption;
    }

    public boolean getCakeOption() {
        return cakeOption;
    }

    public void setPartyUniqueCode(int partyUniqueCode) {
        this.partyUniqueCode = partyUniqueCode;
    }

    public void setClownOption(Boolean clownOption) {
        this.clownOption = clownOption;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setKids(int kids) {
        this.kids = kids;
    }

    public void setMenuOption(boolean menuOption) {
        this.menuOption = menuOption;
    }

    public void setCakeOption(boolean cakeOption) {
        this.cakeOption = cakeOption;
    }
      public void setPartyTime(String partyTime) {
        this.partyTime = partyTime;
    }
     public void setMonth(int month) {
        this.month = month;
    }
}
