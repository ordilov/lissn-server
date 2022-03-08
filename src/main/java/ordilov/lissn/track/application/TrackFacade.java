package ordilov.lissn.track.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ordilov.lissn.track.domain.TrackService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackFacade {

  private final TrackService trackService;

}
