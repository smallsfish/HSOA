package com.hassdata.hserp.service.chapter.impl;

import com.hassdata.hserp.base.BaseDao;
import com.hassdata.hserp.base.BaseServiceImpl;
import com.hassdata.hserp.dao.AdministrativeChapterMapper;
import com.hassdata.hserp.po.AdministrativeChapter;
import com.hassdata.hserp.service.chapter.ChapterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("chapterService")
public class ChapterImpl extends BaseServiceImpl<AdministrativeChapter> implements ChapterService {

    @Resource
    private AdministrativeChapterMapper administrativeChapterMapper;

    @Override
    public BaseDao<AdministrativeChapter> getMapper() {
        return this.administrativeChapterMapper;
    }

}
