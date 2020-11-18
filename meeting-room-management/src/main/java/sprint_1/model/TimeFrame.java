package sprint_1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class TimeFrame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTime;

    private String timeValue;

    @JsonBackReference
    @OneToMany(mappedBy = "startTime", cascade = CascadeType.ALL)
    private Collection<BookedRoom> bookedRoomCollection_1;

    @JsonBackReference
    @OneToMany(mappedBy = "endTime", cascade = CascadeType.ALL)
    private Collection<BookedRoom> getBookedRoomCollection_2;

    //Ai muốn tạo constructor có đối số thì nhớ tạo thêm constructor không đối số nhé!!!

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public String getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(String timeValue) {
        this.timeValue = timeValue;
    }

    public Collection<BookedRoom> getBookedRoomCollection_1() {
        return bookedRoomCollection_1;
    }

    public void setBookedRoomCollection_1(Collection<BookedRoom> bookedRoomCollection_1) {
        this.bookedRoomCollection_1 = bookedRoomCollection_1;
    }

    public Collection<BookedRoom> getGetBookedRoomCollection_2() {
        return getBookedRoomCollection_2;
    }

    public void setGetBookedRoomCollection_2(Collection<BookedRoom> getBookedRoomCollection_2) {
        this.getBookedRoomCollection_2 = getBookedRoomCollection_2;
    }
}
