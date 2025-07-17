/****************************************************************
 * 
 * API 테이블 컬럼 명칭 변경
 * 
 * API TYPE -> DOWNLOAD_TYPE
 * 
****************************************************************/
ALTER TABLE GREG_API ADD COLUMN DOWNLOAD_TYPE SMALLINT DEFAULT -1 COMMENT '다운로드 방식';
UPDATE GREG_API SET DOWNLOAD_TYPE = API_TYPE;
UPDATE GREG_API SET DOWNLOAD_TYPE = 40 WHERE DOWNLOAD_TYPE = 30 ;

ALTER TABLE GREG_API DROP COLUMN API_TYPE;
