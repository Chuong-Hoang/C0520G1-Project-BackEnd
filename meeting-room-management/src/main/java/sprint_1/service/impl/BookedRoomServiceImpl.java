package sprint_1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_1.dto.BookedRoomDTOList;
import sprint_1.model.BookedRoom;
import sprint_1.repository.BookedRoomRepository;
import sprint_1.service.BookedRoomService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookedRoomServiceImpl implements BookedRoomService {
    @Autowired
    private BookedRoomRepository bookedRoomRepository;

    @Override
    public List<BookedRoom> findAll() {
        return bookedRoomRepository.findAll();
    }

    @Override
    public BookedRoom findById(Long id) {
        return bookedRoomRepository.findById(id).orElse(null);
    }

    @Override
    public void save(BookedRoom bookedRoom) {
        bookedRoomRepository.save(bookedRoom);
    }

    @Override
    public void deleteById(Long id) {
        bookedRoomRepository.deleteById(id);
    }

    @Override
    public List<BookedRoom> searchTime(  String startDate, String endDate) throws ParseException {
        List<BookedRoom> resultSearchTime = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse(startDate);
        Date date2 = formatter.parse(endDate);

        for (int i=0;i< bookedRoomRepository.findAll().size(); i++){
            if(date1.compareTo(formatter.parse(bookedRoomRepository.findAll().get(i).getStartDate()))<0 &&
                    date2.compareTo(formatter.parse(bookedRoomRepository.findAll().get(i).getStartDate()))>0){
                resultSearchTime.add(bookedRoomRepository.findAll().get(i));
            }
        }
        return resultSearchTime;
    }


    @Override
    public List<BookedRoom> findAllByMeetingRoom_RoomName(String roomName) {
        return bookedRoomRepository.findAllByMeetingRoom_RoomName(roomName);
    }

    @Override
    public List<BookedRoom> findAllByMonth(String month) {
        List<BookedRoom> monthLists = new ArrayList<>();
        for (int i=0; i< bookedRoomRepository.findAll().size(); i++){
            String[] date = bookedRoomRepository.findAll().get(i).getStartDate().split("-");
            if(month.equals(date[1])){
                monthLists.add(bookedRoomRepository.findAll().get(i));
            }
        }
        return monthLists;
    }

    @Override
    public List<BookedRoom> findAllByYear(String yaer) {
        List<BookedRoom> yearLists = new ArrayList<>();
        for (int i=0; i< bookedRoomRepository.findAll().size(); i++){
            String[] date = bookedRoomRepository.findAll().get(i).getStartDate().split("-");
            if(yaer.equals(date[0])){
                yearLists.add(bookedRoomRepository.findAll().get(i));
            }
        }
        return yearLists;
    }
}
