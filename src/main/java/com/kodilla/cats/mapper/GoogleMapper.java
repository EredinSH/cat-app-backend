package com.kodilla.cats.mapper;

import com.kodilla.cats.domain.GoogleSearch;
import com.kodilla.cats.domain.GoogleSearchDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class GoogleMapper {

    public List<GoogleSearchDto> mapToGoogleSearchDto(final List<GoogleSearch> googleSearch) {
        return googleSearch.stream()
                .map(google -> new GoogleSearchDto(google.getLink()))
                .collect(toList());
    }

    public List<GoogleSearch> mapToGoogleSearch(final List<GoogleSearchDto> googleSearchDto) {
        return googleSearchDto.stream()
                .map(searchDto ->
                        new GoogleSearch(searchDto.getLink()))
                .collect(toList());
    }

}
