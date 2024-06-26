package net.engineeringdigest.journalApp.Services;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

@Autowired
private JournalEntryRepository journalEntryRepository;

@Autowired
private UserService userService;

@Transactional
public void saveEntry(JournalEntry journalEntry , String userName){
   try{
       User user=userService.findByUserName(userName);
       journalEntry.setDate(LocalDateTime.now());
      JournalEntry saved= journalEntryRepository.save(journalEntry);
      user.getJournalEntries().add(saved);
      userService.saveNewUser(user);
   }
   catch(Exception e){
       log.error("Exception",e);
    }
}

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);

    }

public List<JournalEntry>getAll(){
    return journalEntryRepository.findAll();
}

public Optional<JournalEntry> findById(ObjectId id){
    return journalEntryRepository.findById(id);
}

@Transactional
public boolean deleteById(ObjectId id, String userName){
    boolean removed=false;
    try {
        User user = userService.findByUserName(userName);
         removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if (removed) {
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
        }
    }catch(Exception e) {
        System.out.println(e);
        throw new RuntimeException("An error Occurred while deleting th entry" ,e);
    }
    return removed;
}


}
//Controller -->  Service  --> Repository