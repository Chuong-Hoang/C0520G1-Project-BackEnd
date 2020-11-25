package sprint_1.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class TimeFrame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTime;

    private String timeValue;

    @OneToMany(mappedBy = "startTime", cascade = CascadeType.ALL)
    private Collection<BookedRoom> bookedRoomCollection_1;

    @OneToMany(mappedBy = "endTime", cascade = CascadeType.ALL)
    private Collection<BookedRoom> bookedRoomCollection_2;

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

    public Collection<BookedRoom> getBookedRoomCollection_2() {
        return bookedRoomCollection_2;
    }

    public void setBookedRoomCollection_2(Collection<BookedRoom> bookedRoomCollection_2) {
        this.bookedRoomCollection_2 = bookedRoomCollection_2;
    }
}
