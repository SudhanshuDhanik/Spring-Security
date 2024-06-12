package net.engineeringdigest.journalApp.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data             //it is collection of some annotations which also includes @ArgsConstructor
@NoArgsConstructor//Therefore we have to use @NoArgsConstructor also
public class JournalEntry {
     @Id
    private ObjectId id;
     @NonNull
    private String title;
    private String content;
   private LocalDateTime date;



    }


