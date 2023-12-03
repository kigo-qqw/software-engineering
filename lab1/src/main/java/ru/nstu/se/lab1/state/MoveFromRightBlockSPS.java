package ru.nstu.se.lab1.state;

import javafx.application.Platform;
import ru.nstu.se.lab1.state.dto.MergeLoopStateDto;
import ru.nstu.se.lab1.view.SortingProcessView;

public class MoveFromRightBlockSPS<T extends Comparable<T>> extends SortingProcessState<T> {
    private final MergeLoopStateDto<T> mergeLoopStateDto;

    public MoveFromRightBlockSPS(SortingProcessView<T> sortingProcessView,
                                 MergeLoopStateDto<T> mergeLoopStateDto) {
        super(sortingProcessView);
        this.mergeLoopStateDto = mergeLoopStateDto;
    }

    @Override
    public SortingProcessState<T> next() {
        var array = mergeLoopStateDto.getGlobalStateDto().getArray();
        var iterator = mergeLoopStateDto.getIterator();
        var rightBlock = mergeLoopStateDto.getGlobalStateDto().getRightBlock();
        var rightBlockIterator = mergeLoopStateDto.getRightBlockIterator();
        var k = mergeLoopStateDto.getK();

        array.set(iterator, rightBlock.get(k + rightBlockIterator));

//        Platform.runLater(() -> {
            getSortingProcessView().moveFromRightBlockToArray(
                    rightBlock,
                    k + rightBlockIterator,
                    array,
                    iterator);
//        });

        var newMergeLoopStateDto = new MergeLoopStateDto<>(
                mergeLoopStateDto.getGlobalStateDto(),
                iterator + 1,
                mergeLoopStateDto.getLeftBlockIterator(),
                mergeLoopStateDto.getRightBlockIterator() + 1,
                k
        );
        return new MergeLoopSPS<>(getSortingProcessView(), newMergeLoopStateDto);
    }
}
