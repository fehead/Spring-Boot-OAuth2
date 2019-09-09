package im.fehead.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import im.fehead.domain.Board;
import im.fehead.domain.User;

/**
 * Created by KimYJ on 2017-07-12.
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	Board findByUser(User user);
}
