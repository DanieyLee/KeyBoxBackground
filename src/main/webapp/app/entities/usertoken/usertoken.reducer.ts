import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IUsertoken, defaultValue } from 'app/shared/model/usertoken.model';

export const ACTION_TYPES = {
  FETCH_USERTOKEN_LIST: 'usertoken/FETCH_USERTOKEN_LIST',
  FETCH_USERTOKEN: 'usertoken/FETCH_USERTOKEN',
  CREATE_USERTOKEN: 'usertoken/CREATE_USERTOKEN',
  UPDATE_USERTOKEN: 'usertoken/UPDATE_USERTOKEN',
  DELETE_USERTOKEN: 'usertoken/DELETE_USERTOKEN',
  RESET: 'usertoken/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IUsertoken>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type UsertokenState = Readonly<typeof initialState>;

// Reducer

export default (state: UsertokenState = initialState, action): UsertokenState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_USERTOKEN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_USERTOKEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_USERTOKEN):
    case REQUEST(ACTION_TYPES.UPDATE_USERTOKEN):
    case REQUEST(ACTION_TYPES.DELETE_USERTOKEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_USERTOKEN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_USERTOKEN):
    case FAILURE(ACTION_TYPES.CREATE_USERTOKEN):
    case FAILURE(ACTION_TYPES.UPDATE_USERTOKEN):
    case FAILURE(ACTION_TYPES.DELETE_USERTOKEN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERTOKEN_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERTOKEN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_USERTOKEN):
    case SUCCESS(ACTION_TYPES.UPDATE_USERTOKEN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_USERTOKEN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/usertokens';

// Actions

export const getEntities: ICrudGetAllAction<IUsertoken> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_USERTOKEN_LIST,
    payload: axios.get<IUsertoken>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IUsertoken> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_USERTOKEN,
    payload: axios.get<IUsertoken>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IUsertoken> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_USERTOKEN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IUsertoken> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_USERTOKEN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IUsertoken> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_USERTOKEN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
