package ru.nstu.se.lab1.state;

import ru.nstu.se.lab1.state.dto.MergeLoopStateDto;
import ru.nstu.se.lab1.view.SortingProcessView;

public class CompareSPS<T extends Comparable<T>> extends SortingProcessState<T> {
    private final MergeLoopStateDto<T> mergeLoopStateDto;

    public CompareSPS(SortingProcessView<T> sortingProcessView, MergeLoopStateDto<T> mergeLoopStateDto) {
        super(sortingProcessView);
        this.mergeLoopStateDto = mergeLoopStateDto;
    }

    @Override
    public SortingProcessState<T> next() {
        var leftBlock = mergeLoopStateDto.getGlobalStateDto().getLeftBlock();
        var rightBlock = mergeLoopStateDto.getGlobalStateDto().getRightBlock();
        var leftBlockIterator = mergeLoopStateDto.getLeftBlockIterator();
        var rightBlockIterator = mergeLoopStateDto.getRightBlockIterator();
        var k = mergeLoopStateDto.getK();

        boolean compareResult = leftBlock.get(k + leftBlockIterator).compareTo(rightBlock.get(k + rightBlockIterator)) < 0;

        getSortingProcessView().clearGroupBorders();
        getSortingProcessView().drawGroupBorders(mergeLoopStateDto.getIterator(), k, mergeLoopStateDto.getGlobalStateDto().getGroupSize());
        getSortingProcessView().compare(k + leftBlockIterator, k + rightBlockIterator, compareResult);

        if (compareResult) {
            return new MoveFromLeftBlockSPS<>(getSortingProcessView(), mergeLoopStateDto);
        } else {
            return new MoveFromRightBlockSPS<>(getSortingProcessView(), mergeLoopStateDto);
        }
    }
}
