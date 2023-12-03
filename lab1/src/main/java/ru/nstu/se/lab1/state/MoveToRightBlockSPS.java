package ru.nstu.se.lab1.state;

import ru.nstu.se.lab1.state.dto.MoveToBlockStateDto;
import ru.nstu.se.lab1.view.SortingProcessView;

public class MoveToRightBlockSPS<T extends Comparable<T>> extends SortingProcessState<T> {
    private final MoveToBlockStateDto<T> moveToBlockStateDto;

    public MoveToRightBlockSPS(SortingProcessView<T> sortingProcessView, MoveToBlockStateDto<T> moveToBlockStateDto) {
        super(sortingProcessView);
        this.moveToBlockStateDto = moveToBlockStateDto;
    }

    @Override
    public SortingProcessState<T> next() {
        var iterator = moveToBlockStateDto.getIterator();
        var leftBlockSize = moveToBlockStateDto.getGlobalStateDto().getLeftBlockSize();
        var rightBlockSize = moveToBlockStateDto.getGlobalStateDto().getRightBlockSize();

        if (iterator < rightBlockSize) {
            var array = moveToBlockStateDto.getGlobalStateDto().getArray();
            var rightBlock = moveToBlockStateDto.getGlobalStateDto().getRightBlock();

            rightBlock.set(iterator, array.get(iterator + leftBlockSize));
            getSortingProcessView().moveFromArrayToRightBlock(array, iterator + leftBlockSize, rightBlock, iterator);
            moveToBlockStateDto.setIterator(iterator + 1);

            return new MoveToRightBlockSPS<>(getSortingProcessView(), moveToBlockStateDto);
        }

        return new AllValuesMovedToRightBlockSPS<>(getSortingProcessView(), moveToBlockStateDto.getGlobalStateDto());
    }
}
