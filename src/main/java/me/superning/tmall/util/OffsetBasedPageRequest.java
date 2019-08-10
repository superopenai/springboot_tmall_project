package me.superning.tmall.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class OffsetBasedPageRequest implements Pageable, Serializable
{
    private int limit;
    private int offset;
    private final Sort sort;

    /**
     *
     * @param  limit 限制元素数量
     * @param offset 偏移量
     * @param sort 排序
     */
    public OffsetBasedPageRequest(int limit, int offset, Sort sort) {

        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }

        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }

    }

    public OffsetBasedPageRequest(int offset, int limit) {
        this(offset, limit, new Sort(Sort.Direction.DESC,"id"));
    }






    @Override
    public int getPageNumber() {
        return offset/limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;

    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetBasedPageRequest((int) (getOffset()+getPageSize()),getPageSize(),getSort());
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    private Pageable previous() {
        return hasPrevious() ? new OffsetBasedPageRequest((int) (getOffset() - getPageSize()), getPageSize(), getSort()) : this;

    }

    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(0, getPageSize(), getSort());

    }

    @Override
    public boolean hasPrevious() {
        return offset>limit;
    }

    @Override
    public String toString() {
        return "OffsetBasedPageRequest{" +
                "limit=" + limit +
                ", offset=" + offset +
                ", sort=" + sort +
                '}';
    }
}
