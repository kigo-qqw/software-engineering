package ru.nstu.se.lab1.state;

import javafx.application.Platform;
import ru.nstu.se.lab1.state.dto.MergeLoopStateDto;
import ru.nstu.se.lab1.view.SortingProcessView;

public class MoveFromLeftBlockSPS<T extends Comparable<T>> extends SortingProcessState<T> {
    private final MergeLoopStateDto<T> mergeLoopStateDto;

    public MoveFromLeftBlockSPS(SortingProcessView<T> sortingProcessView,
                                MergeLoopStateDto<T> mergeLoopStateDto) {
        super(sortingProcessView);
        this.mergeLoopStateDto = mergeLoopStateDto;
    }

    @Override
    public SortingProcessState<T> next() {
        var array = mergeLoopStateDto.getGlobalStateDto().getArray();
        var iterator = mergeLoopStateDto.getIterator();
        var leftBlock = mergeLoopStateDto.getGlobalStateDto().getLeftBlock();
        var leftBlockIterator = mergeLoopStateDto.getLeftBlockIterator();
        var k = mergeLoopStateDto.getK();

        array.set(iterator, leftBlock.get(k + leftBlockIterator));

//        Platform.runLater(() -> {
            getSortingProcessView().moveFromLeftBlockToArray(
                    leftBlock,
                    k + leftBlockIterator,
                    array,
                    iterator);
//        });

        var newMergeLoopStateDto = new MergeLoopStateDto<>(
                mergeLoopStateDto.getGlobalStateDto(),
                iterator + 1,
                leftBlockIterator + 1,
                mergeLoopStateDto.getRightBlockIterator(),
                k
        );
        return new MergeLoopSPS<>(getSortingProcessView(), newMergeLoopStateDto);
    }
}