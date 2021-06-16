//package dmj.movie.stats.sb;
//
//import dmj.movie.stats.common.dto.CastDto;
//import dmj.movie.stats.common.dto.DirectorDto;
//import dmj.movie.stats.common.dto.MovieDto;
//import dmj.movie.stats.common.dto.ScreenwriterDto;
//import dmj.movie.stats.common.enums.Gender;
//import dmj.movie.stats.common.enums.Genre;
//import dmj.movie.stats.common.ServiceMovieStatsWeb;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class Scheduler {
//
//    @Autowired
//    ServiceMovieStatsWeb serviceMovieStatsWeb;
//
//    @Scheduled(cron="0/30 * * * * *")
//    public void schedule() {
//        try {
//
//            MovieDto movieDto = new MovieDto("Name", LocalDate.now(), Genre.ACTION, 140, false);
//            CastDto castDto = new CastDto();
//            castDto.setAge(50);
//            castDto.setBirthday(LocalDate.of(1970, 12,12));
//            castDto.setGender(Gender.MALE);
//            castDto.setName("name");
//            DirectorDto directorDto = new DirectorDto();
//            directorDto.setAge(50);
//            directorDto.setBirthday(LocalDate.of(1970, 12,12));
//            directorDto.setGender(Gender.MALE);
//            directorDto.setName("name");
//            ScreenwriterDto screenwriterDto = new ScreenwriterDto();
//            screenwriterDto.setAge(50);
//            screenwriterDto.setBirthday(LocalDate.of(1970, 12,12));
//            screenwriterDto.setGender(Gender.MALE);
//            screenwriterDto.setName("name");
//
//            movieDto.setScreenwriters(Arrays.asList(screenwriterDto));
//            movieDto.setCasts(Arrays.asList(castDto));
//            movieDto.setDirectors(Arrays.asList(directorDto));
//
////            serviceMovieStatsWeb.update(movieDto);
//            MovieDto md = serviceMovieStatsWeb.find("5a8152e9-8f8f-4d07-9480-54d0ee59213a");
//            List<MovieDto> list = serviceMovieStatsWeb.lists();
//            System.out.println("Complete");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//
//}
