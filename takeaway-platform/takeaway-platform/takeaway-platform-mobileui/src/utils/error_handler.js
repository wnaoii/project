import {Message} from "element-ui";

function handleError(err) {
  if(err.isAxiosError) {
    console.log("isAxiosError")
    if(err.response && err.response.data) {
      console.log(err.response.data)
      if(err.response.status === 400) {
        Message.error({
          message: '交互异常，稍后重试'
        })
      } else if(err.response.status === 403) {
        console.error(err.response.data.message)
        Message.error({
          message: err.response.data.message
        })
      } else {
        Message.error({
          message: '系统异常，稍后重试'
        })
      }
    } else {
      console.error('api异常')
      Message.error({
        message: '数据获取异常，请稍后重试'
      })
    }
  }else {
    console.error(err)
    Message.error({
      message: '系统异常，请刷新后重试'
    })
  }
}

export default {
  errorHandler: handleError
}
