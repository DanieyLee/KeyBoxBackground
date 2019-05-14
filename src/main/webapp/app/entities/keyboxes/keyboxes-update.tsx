import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUsertoken } from 'app/shared/model/usertoken.model';
import { getEntities as getUsertokens } from 'app/entities/usertoken/usertoken.reducer';
import { getEntity, updateEntity, createEntity, reset } from './keyboxes.reducer';
import { IKeyboxes } from 'app/shared/model/keyboxes.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IKeyboxesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IKeyboxesUpdateState {
  isNew: boolean;
  usertokenId: string;
}

export class KeyboxesUpdate extends React.Component<IKeyboxesUpdateProps, IKeyboxesUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      usertokenId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getUsertokens();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { keyboxesEntity } = this.props;
      const entity = {
        ...keyboxesEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/keyboxes');
  };

  render() {
    const { keyboxesEntity, usertokens, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="keyboxApp.keyboxes.home.createOrEditLabel">
              <Translate contentKey="keyboxApp.keyboxes.home.createOrEditLabel">Create or edit a Keyboxes</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : keyboxesEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="keyboxes-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameLabel" for="name">
                    <Translate contentKey="keyboxApp.keyboxes.name">Name</Translate>
                  </Label>
                  <AvField id="keyboxes-name" type="text" name="name" />
                </AvGroup>
                <AvGroup>
                  <Label id="loginLabel" for="login">
                    <Translate contentKey="keyboxApp.keyboxes.login">Login</Translate>
                  </Label>
                  <AvField id="keyboxes-login" type="text" name="login" />
                </AvGroup>
                <AvGroup>
                  <Label id="passwordtextLabel" for="passwordtext">
                    <Translate contentKey="keyboxApp.keyboxes.passwordtext">Passwordtext</Translate>
                  </Label>
                  <AvField id="keyboxes-passwordtext" type="text" name="passwordtext" />
                </AvGroup>
                <AvGroup>
                  <Label id="levelpasswordtextLabel" for="levelpasswordtext">
                    <Translate contentKey="keyboxApp.keyboxes.levelpasswordtext">Levelpasswordtext</Translate>
                  </Label>
                  <AvField id="keyboxes-levelpasswordtext" type="text" name="levelpasswordtext" />
                </AvGroup>
                <AvGroup>
                  <Label id="addressLabel" for="address">
                    <Translate contentKey="keyboxApp.keyboxes.address">Address</Translate>
                  </Label>
                  <AvField id="keyboxes-address" type="text" name="address" />
                </AvGroup>
                <AvGroup>
                  <Label id="createDateLabel" for="createDate">
                    <Translate contentKey="keyboxApp.keyboxes.createDate">Create Date</Translate>
                  </Label>
                  <AvField id="keyboxes-createDate" type="string" className="form-control" name="createDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="otherLabel" for="other">
                    <Translate contentKey="keyboxApp.keyboxes.other">Other</Translate>
                  </Label>
                  <AvField id="keyboxes-other" type="text" name="other" />
                </AvGroup>
                <AvGroup>
                  <Label for="usertoken.id">
                    <Translate contentKey="keyboxApp.keyboxes.usertoken">Usertoken</Translate>
                  </Label>
                  <AvInput id="keyboxes-usertoken" type="select" className="form-control" name="usertokenId">
                    <option value="" key="0" />
                    {usertokens
                      ? usertokens.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/keyboxes" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  usertokens: storeState.usertoken.entities,
  keyboxesEntity: storeState.keyboxes.entity,
  loading: storeState.keyboxes.loading,
  updating: storeState.keyboxes.updating,
  updateSuccess: storeState.keyboxes.updateSuccess
});

const mapDispatchToProps = {
  getUsertokens,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(KeyboxesUpdate);
