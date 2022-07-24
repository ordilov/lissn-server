package ordilov.lissn.track.application;

import ordilov.lissn.track.domain.TrackService;
import org.springframework.stereotype.Service;

@Service
class TrackFacade(
  val trackService: TrackService
) {

}