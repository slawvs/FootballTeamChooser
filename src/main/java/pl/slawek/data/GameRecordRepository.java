package pl.slawek.data;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.slawek.model.GameRecord;

public interface GameRecordRepository extends JpaRepository<GameRecord, Long> {

}
