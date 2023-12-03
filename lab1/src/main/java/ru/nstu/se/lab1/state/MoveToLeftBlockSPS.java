package ru.nstu.se.lab1.state;

import ru.nstu.se.lab1.state.dto.MoveToBlockStateDto;
import ru.nstu.se.lab1.view.SortingProcessView;

public class MoveToLeftBlockSPS<T extends Comparable<T>> extends SortingProcessState<T> {
    private final MoveToBlockStateDto<T> moveToBlockStateDto;

    public MoveToLeftBlockSPS(SortingProcessView<T> sortingProcessView, MoveToBlockStateDto<T> moveToBlockStateDto) {
        super(sortingProcessView);
        this.moveToBlockStateDto = moveToBlockStateDto;
    }

    @Override
    public SortingProcessState<T> next() {
        var iterator = moveToBlockStateDto.getIterator();
        if (iterator < moveToBlockStateDto.getGlobalStateDto().getLeftBlockSize()) {
            var array = moveToBlockStateDto.getGlobalStateDto().getArray();
            var leftBlock = moveToBlockStateDto.getGlobalStateDto().getLeftBlock();

            leftBlock.set(iterator, array.get(iterator));
            getSortingProcessView().moveFromArrayToLeftBlock(array, iterator, leftBlock, iterator);

            moveToBlockStateDto.setIterator(iterator + 1);

            return new MoveToLeftBlockSPS<>(getSortingProcessView(), moveToBlockStateDto);
        }

        return new AllValuesMovedToLeftBlockSPS<>(getSortingProcessView(), moveToBlockStateDto.getGlobalStateDto());
    }
}
