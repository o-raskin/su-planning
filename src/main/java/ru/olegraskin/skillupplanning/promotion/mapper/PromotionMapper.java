package ru.olegraskin.skillupplanning.promotion.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.olegraskin.skillupplanning.promotion.domain.Promotion;
import ru.olegraskin.skillupplanning.promotion.dto.PromotionDto;

@Component
@RequiredArgsConstructor
public class PromotionMapper {

    private final ModelMapper modelMapper;

    public Promotion dtoToEntity(PromotionDto dto) {
        return this.modelMapper.map(dto, Promotion.class);
    }


    public PromotionDto entityToDto(Promotion entity) {
        return this.modelMapper.map(entity, PromotionDto.class);
    }
}
