/**
 * 
 */
package at.ac.tuwien.inso.tl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.model.Participation;

/**
 * @author beks
 * 
 */
@Component
public interface ParticipationDao extends JpaRepository<Participation, Integer>
{
}
